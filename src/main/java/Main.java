import com.beust.jcommander.JCommander;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) throws IOException {


        List<String> list;


        List<String> list2;


        CommandLine jArgs = new CommandLine();
        JCommander helloCmd = JCommander.newBuilder()
                .addObject(jArgs)
                .build();

        helloCmd.parse(args);

        URL url = new URL(jArgs.getUrl());



        String host = url.getHost();
        int port = url.getDefaultPort();

        // 소켓 및 입출력 스트림 준비
        Socket socket = new Socket(host, port);
        OutputStream outputStream = socket.getOutputStream();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintStream out = new PrintStream(socket.getOutputStream());
        StringBuilder tmp = new StringBuilder();

        System.out.println(tmp);
        if (jArgs.getCommand() != null) {
            // 요청라인
            tmp.append(jArgs.getCommand() + " "+ url.getPath()+" HTTP/1.1\n");
            out.println(jArgs.getCommand() + " "+ url.getPath()+" HTTP/1.1");

        } else if (jArgs.isNextResponse()) {
            tmp.append("GET" + " "+ url.getPath()+" HTTP/1.1\n");
            out.println("GET" + " "+ url.getPath()+" HTTP/1.1");
        } else {
            tmp.append("GET /get HTTP/1.1\n");
            out.println("GET /get HTTP/1.1");

        }
        tmp.append("Host: " + host+"\n");
        // 헤더정보
        out.println("Host: " + host);

        // 공백라인
        tmp.append("Connection: close \n");
        out.println("Connection: close");



        if(jArgs.getLine()!=null) {
            tmp.append(jArgs.getLine()+"\n");
            out.println(jArgs.getLine());

        }





        tmp.append("\n");
        out.println();

        if(jArgs.getData()!=null){
            tmp.append(jArgs.getData()+"\n");
            out.println(jArgs.getData());
            tmp.append("Content-Length: 16\n");
            out.println("Content-Length: 16");
        }

        System.out.println(tmp);

        // 응답 내용
        String line = null;

        list = new ArrayList<>();
        list2 = new ArrayList<>();
        boolean check = false;
        while ((line = in.readLine()) != null) {

             list.add(line);

            if (line.isEmpty()) {
                check = true;
             }

            if (check) {
                list2.add(line);
            }
         }


        if (jArgs.isVerbose()) {
            for (String s : list) {
                System.out.println(s);
            }
        } else {
            for (String s : list2) {
                System.out.println(s);
            }

            in.close();
            out.close();
            socket.close();

        }
    }
}










