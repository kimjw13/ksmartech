package com.ksmartech.test1;

import java.io.*;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("www.google.com",80);//open port 80

        OutputStream outStream = socket.getOutputStream(); //server info
        InputStream inStream = socket.getInputStream(); //server에서 받은 info
        BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
        PrintWriter writer = new PrintWriter(new BufferedOutputStream(outStream));

        writer.print("GET / HTTP/1.1\nHost:www.google.com\n\n");
        writer.flush();

        System.out.println(readFully(reader));

        socket.close();
    }
    
    private static StringBuilder readFully(Reader in) throws IOException {
        StringBuilder sb = new StringBuilder();
        int BUFFER_SIZE=1024;
        char[] buffer = new char[BUFFER_SIZE];
        int charsRead = 0;
        while ((charsRead  = in.read(buffer, 0, BUFFER_SIZE)) != -1) {
          sb.append(buffer, 0, charsRead);
        }
		return sb;
    }
}