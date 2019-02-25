package main.basic.nio;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by wangyoucai on 2019/2/23.
 */
public class ServerHandler implements Runnable {

    private Selector selector;

    private ServerSocketChannel serverSocketChannel;

    public ServerHandler(int port) {
        try {
            selector = Selector.open();
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("The time server is start in port : " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                selector.select(1000);
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> itr = keys.iterator();
                SelectionKey key = null;
                while (itr.hasNext()) {
                    key = itr.next();
                    itr.remove();
                    handleInput(key);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()){
            // 处理新连接的请求信息
            if (key.isAcceptable()){
                // accept 新的连接
                ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                SocketChannel socketChannel = serverSocketChannel.accept();
                socketChannel.configureBlocking(false);
                // 注册到复用器
                socketChannel.register(selector,SelectionKey.OP_READ);
            }
            // 是否已就绪
            if (key.isReadable()){
                // 读取数据
                SocketChannel socketChannel = (SocketChannel)key.channel();
                ByteBuffer readBuff = ByteBuffer.allocate(1024);
                int readByte = socketChannel.read(readBuff);
                if (readByte > 0){
                    readBuff.flip();
                    byte[] bytes = new byte[readBuff.remaining()];
                    readBuff.get(bytes);
                    String body = new String(bytes,"UTF-8");
                    // 处理响应信息
                    byte[] data = body.getBytes();
                    ByteBuffer writeBuffer = ByteBuffer.allocate(data.length);
                    writeBuffer.put(bytes);
                    writeBuffer.flip();
                    socketChannel.write(writeBuffer);
                }else if (readByte < 0){

                }
            }
        }

    }
}
