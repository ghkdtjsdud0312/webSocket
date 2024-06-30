package com.example.chat;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ChatServer {
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(9999);

        // 공유 객체에서 쓰레드에 안전한 리스트를 만든다.
        // outList은 PrintWriter를 여러 개 갖는 것이다.-> Thread를 만들 때 outList 넣어준다.
        List<PrintWriter> outList = Collections.synchronizedList(new ArrayList<>());

        while (true) {
            Socket socket = serverSocket.accept();

            ChatThread chatThread = new ChatThread(socket, outList);
            chatThread.start();
        }
    }
}

// Thread 상속 받음
class ChatThread extends Thread {

    // 통신하기 위한 것
   private Socket socket;
   private List<PrintWriter> outList;

   // 현재 연결된 클라이언트와 통신하기 위한 것
   public ChatThread(Socket socket, List<PrintWriter> outList) {
       this.socket = socket;
       this.outList = outList;
   }

    public void run() {
        // 1. socket으로부터 읽어들일 수 있는 객체를 받는다.
        // 2. socket에게 쓰기 위한 객체를 얻는다. (현재 연결된 클라이언트에게 쓰는 객체)
        // 3. 클라이언트가 보낸 메세지를 읽는다.
        // 4. 접속된 모든 클라이언트에게 메세지를 보낸다. (현재 접속된 모든 클라이언트에게 쓸 수 있는 객체가 필요하다.)
    }

}
