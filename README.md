The Project: ml-ops-api

This project is a RESTful API for managing Machine Learning Workspaces, Models, and Evaluation Metrics using JAX-RS.

Below feautures are included in the APIs:

⁠Create, retrieve and delete ML Workspaces

⁠Create and list Machine Learning Models
⁠
Filter models by status
⁠
Add and retrieve Evaluation Metrics for models
⁠
Prevent deletion of workspaces that contain models
⁠
Prevent adding metrics to deprecated models
⁠
Root discovery endpoint providing API metadata


_Answers to the questions given_

Question: 
When returning a Java object from a method, it is automatically serialised into
JSON. Explain the role of a MessageBodyWriter or a JSON provider (like Jackson) in this con
version process.
Answer: 
When a JAX-RS method returns a Java object, it cannot be sent directly to the client because web browsers and applications communicate via JSON or XML. A MessageBodyWriter  will convert the Java object into a format that can be included in the HTTP response.
A JSON provider such as Jackson provides a MessageBodyWriter that automatically converts Java objects into JSON. It reads the object's fields or getter methods and creates the matching JSON structure. Due to this developers do not need to write JSON strings manually and safe as it aligns with POJOs.


Question: 
REST architecture dictates that APIs should be strictly ’stateless’. Define what
statelessness means in this context and explain why it makes cloud APIs easier to scale
horizontally across multiple servers.
Answer: 
In REST, statelessness means that the server does not remember information about previous session from a client. Every concurrent or new requests should include information required to understand by the server to continue the sessions. i.e. auth details etc. 
Since servers do not keep client session data any request can be handled by any server hence it is easier to scale the application horizontally.


Question: 
Discuss how implementing HTTP Cache-Control headers on the GETworkspaces
endpoint could improve performance for the client and reduce unnecessary processing
load on the server.
Answer: 
By using Cache-Control headers allows clients to temporarily store the response from the GET /workspaces endpoint. If the data has not changed, the client could use the cached response instead of sending another request to the server. By doing this it improves performance, CPU processing time and faster loading time. In terms of network end, it helps to reduce bandwidth usage. 


Question: 
If aclient needs to verify whether a specific workspace exists but wants to save
bandwidthbynotdownloading the entireJSONbody,whichHTTPmethodshouldtheyuse
instead of GET? Explain your reasoning.
Answer:
We could you use HEAD method as it only returns HTTP response instead of loading the response body so it saves bandwidth as well. 
If workspace exists then HEAD will return 200 Status otherwise 400 Not Found.


Question: 
When creating a new Model via a POST request, it is considered best practice
for the server to generate the unique id(e.g., usingUUID.randomUUID()) rather than allow
ing the client to pass an id in their JSON payload. Discuss the security and data integrity
reasons behind this architectural choice.
Answer: 
It is better for the server to generate the unique ID because then every model contains unique ID and accidentally entering same ID is prevented. This also helps to avoid duplicates going into the system.
In terms of security and data integrity, users of the system cannot guess the unique ID hence they cannot fetch records via the ID otherwise they can retrieve sensitive information etc. Since application generates the ID, it is always unique. 


Question: 
If a user attempts to search for a framework containing spaces or special char
acters (e.g., ?framework=Scikit Learn & Tools), how must the client modify the URL, and
why is this encoding necessary?
Answer:
The client needs to encode the query parameter before request is triggered. If URL encoding is not done, request parameters will not be understood properly results in 400 bad request. to prevent these issues, URL encoding is ideal.


Question: 
You can place annotations like @Produces(MediaType.APPLICATION_JSON) at ei
ther the class level or the individual method level. What is the benefit of class-level place
ment, and how does method-level overriding work?
Answer:
Placing @Produces(MediaType.APPLICATION_JSON) at the class level makes JSON the default response format for all methods in that class. so easier to maintain and no need to add it for each and every methods in the class so easier to maintain as need to write only once. 
In case if other method needs to return different response media type, then @Produces can be added to the method which then overrides the class @Produces only for this specific method while rest of the methods stick to class @Produces response type.


Question: 
HTTP status codes are categorised into classes(e.g., 2xx, 4xx, 5xx). Explain fundamentally why avalidation failure caused by the user providing a non-existentworkspace Id
must return a 4xx code rather than a 5xx code.
Answer:
A non-existent workspaceId should return a 4xx status code because the error is caused by the client's request as client has provided invalid input to access non existing workspace. The server is able to identify it correctly by detecting the invalid request and throwing Not Found 404.  A 5xx status code can be used only if server is unable to process the request hence error in the backend.


Question: 
If an operation throws a specific custom exception
(e.g., LinkedWorkspaceNotFoundException)and you also have a global Exception Mapper<Throwable>,
how does the JAX-RS runtime determine which mapper to execute?
Answer:
The runtime selects the most specific ExceptionMapper that matches the thrown exception. If a LinkedWorkspaceNotFoundException is thrown, the corresponding ExceptionMapper<LinkedWorkspaceNotFoundException> is used. The global ExceptionMapper<Throwable> is only used when there is no more specific mapper available. This allows specific exceptions to return customised responses while the global mapper acts as a fallback for unexpected errors as a default error handler.


Question: 
In your filter,you interact with Container Request Context and Container Response Context.
List two pieces of crucial HTTP metadata (e.g., headers, URIs) you can extract from these
contexts that are highly valuable for debugging server issues.
Answer: 
Request URI: Shows which endpoint the client accessed, making it easier to identify where an error occurred.
HTTP Status Code: Shows the outcome of the request (such as 200 OK, 404 Not Found, or 500 Internal Server Error), helping developers to quickly find whether the request was successful or why it failed
