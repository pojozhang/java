package example.protobuf;

import com.google.protobuf.Any;
import example.protobuf.people.ApiResponseOuterClass.ApiResponse;
import example.protobuf.people.PeopleOuterClass.People;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        People people = People.newBuilder().setName("proto").setAge(1).build();
        ApiResponse response = ApiResponse.newBuilder().setData(Any.pack(people)).build();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        response.writeTo(outputStream);

        ApiResponse r = ApiResponse.parseFrom(outputStream.toByteArray());
        System.out.println(r.getData().getTypeUrl());
        System.out.println(r.getData().unpack(People.class).getName());
    }
}
