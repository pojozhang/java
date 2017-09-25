package example.protobuf;

import com.google.protobuf.Any;
import example.protobuf.people.People;
import example.protobuf.people.ResponseOuterClass.Response;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        People people = People.newBuilder().setName("proto").setAge(1).setBirthDate(1000000).build();
        Response response = Response.newBuilder().setData(Any.pack(people)).putHeaders("Content-Type", "pb").build();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        response.writeTo(outputStream);

        Response r = Response.parseFrom(outputStream.toByteArray());
        System.out.println(r.getHeadersMap());
        People p = r.getData().unpack(People.class);
        System.out.println(r.getData().getTypeUrl());
        System.out.println(p.getName());
        System.out.println(p.getAge());
        System.out.println(p.getBirthDate());
    }
}
