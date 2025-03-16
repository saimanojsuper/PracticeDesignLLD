package org.practice.concurrency;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CallingAPIAsync {

  public static void main(String... Args){
    // Number of synchronous requests you want to make
    int numberOfRequests = 20;

    long currentTimeMillis = System.currentTimeMillis();
    callApiAysnc(numberOfRequests);
    long endTime = System.currentTimeMillis();
    System.out.println("async val: "+(endTime-currentTimeMillis)); // for 300 it took: 1911ms

    long syncCurrentTimeMillis = System.currentTimeMillis();
    runSynchronously(numberOfRequests);
    long syncEndTime = System.currentTimeMillis();
    System.out.println("sync calls: "+(syncEndTime-syncCurrentTimeMillis)); // for 300 it took: 64330ms
  }

  public static void callApiAysnc(int numberOfRequests) {
    // Create an HttpClient
    HttpClient client = HttpClient.newHttpClient();

    List<CompletableFuture> futures = new ArrayList<>();


    for (int i = 0; i < numberOfRequests; i++) {
      // API endpoint
      URI uri = URI.create("https://dummyjson.com/users/"+i);

      // Create the HttpRequest
      HttpRequest request = HttpRequest.newBuilder().uri(uri).build();
      futures.add(client.sendAsync(request, HttpResponse.BodyHandlers.ofString()));
    }

    // Send the request asynchronously
    //    CompletableFuture<HttpResponse<String>> responseFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());

    // Use CompletableFuture.allOf to wait for all API calls to complete
    CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

    // Handle the responses once all requests are done
    allOf.thenRun(() -> {
      for (CompletableFuture<HttpResponse<String>> future : futures) {
        try {
          // Get the response (this blocks until the response is ready)
          HttpResponse<String> response = future.get();
          if (response.statusCode() == 200) {
            System.out.println("Response received: " + response.body());
          } else {
            //System.out.println("Error: " + response.statusCode());
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }).join();
  }

  public static void runSynchronously(int numberOfRequests){
    HttpClient client = HttpClient.newHttpClient();


    // Make multiple synchronous API calls
    for (int i = 0; i < numberOfRequests; i++) {

      // Send the request synchronously
      try {
        // API endpoint
        URI uri = URI.create("https://dummyjson.com/users/"+i);
        // Create the HttpRequest
        HttpRequest request = HttpRequest.newBuilder()
            .uri(uri)
            .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Check for successful response
        if (response.statusCode() == 200) {
          System.out.println("Response received for request " + (i + 1) + ": " + response.body());
        } else {
          //System.out.println("Error (Request " + (i + 1) + "): " + response.statusCode());
        }
      } catch (IOException | InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
