package sockets.connect.sender;

import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;

public class Sender {
    private DataInputStream dis;
    private DataOutputStream dos;
    private final Gson gson = new Gson();

    public Sender(Socket socket){
        try {
            OutputStream os = socket.getOutputStream();
            dos = new DataOutputStream(os);
            InputStream is = socket.getInputStream();
            dis = new DataInputStream(is);
        } catch (IOException ex) {  }
    }

    public void sendRequest(Request req)
    {
        try {
            String s = gson.toJson(req);
            dos.writeUTF(s);
        } catch (IOException ex) { }
    }

    public void sendResp(Response resp)
    {
        try {
            String s = gson.toJson(resp);
            dos.writeUTF(s);
        } catch (IOException ex) { }
    }

    public Request getRequest() throws IOException {
        String s = dis.readUTF();
        return gson.fromJson(s, Request.class);
    }
    public Response getResp() throws IOException {
        String s = dis.readUTF();
        return gson.fromJson(s, Response.class);
    }
}
