# HRS Tech Assignment - Hotel Booking Service
### Introduce
    * This hotel booking is to serve for individual hotel booking.
### 1. This documentation is to introduce the completed features and limitations of the solution.
For further reference, please consider the following sections:
#### Completed features:
* Search for hotel
* Create Hotel Booking
* View Hotel booking details.
#### Limitations:
* Search for hotel
    * Just support for field City and Country
* Create/Update Hotel Booking
    * There is no payment integration or fields to identify the finished payment.
    * No support for group of customers.
* View Hotel Booking Details
    * No support for viewing multiple bookings and pagination.
* Global endpoint
    * The validation is limited in checking existing of booking, hotel and room.
    * This is a monolith service, not decentralized microservice architecture. But it can be extended in near future.
    * Unit testing could cover the happy cases only.
    * The error messages are not localized.
    * In-memory database mode H2 is used so data is not permanently stored.
    * The validation for country, phone number, or other fields are not supported.

### 2. The technology:
* ####  Java 11.
* #### Springboot 2.x.x
* #### Spring data jpa.
* #### Springboot web.
* #### H2 in-memory database.
* #### Flyway for migration.
* #### Maven for project building and packaging

### 3. 3rd party libraries:
* #### Lombok - create boilerplate code of constructors, set, get methods.
* #### Mapstruct for mapping attribute values.

### 4. Solution:
* Design:
    * #### Simple architect:
  ![Screenshot 2024-03-11 at 17.21.12.png](images%2FScreenshot%202024-03-11%20at%2017.21.12.png)
    * #### Simple database diagram:
  ![Screenshot 2024-03-11 at 17.21.54.png](images%2FScreenshot%202024-03-11%20at%2017.21.54.png)
    * #### Simple class diagram
  ![Screenshot 2024-03-11 at 17.22.15.png](images%2FScreenshot%202024-03-11%20at%2017.22.15.png)

### 5. Guide
* Generate data using flyway:
    * Migration script will automatically run when application startup.
      <br><b>Note</b>: Database is in the in-memory mode, so after every time we restart the endpoint application, the records will be clear.
      <br>We can make it to H2 filesystem mode url for permanent storage.
    * Migrations scripts:
        * V1_0_0_0__schema.sql -> initialize schema.
          [V1_0_0_0__schema.sql](src%2Fmain%2Fresources%2Fdb%2Fmigration%2FV1_0_0_0__schema.sql)
        * V1_0_0_1__initialize -> initialize records.
          [V1_0_0_1__initialize.sql](src%2Fmain%2Fresources%2Fdb%2Fmigration%2FV1_0_0_1__initialize.sql)
        * V1_0_0_2__Add_Thailand_hotel -> Add more records of hotel, customer, room.
          [V1_0_0_2__Add_thailand_hotel.sql](src%2Fmain%2Fresources%2Fdb%2Fmigration%2FV1_0_0_2__Add_thailand_hotel.sql)
    * I assume that the domain of this endpoint is for Hotel Booking and so the records of Hotel, Room and customers should be existing before customer make a booking.
    * Build and Run:
        * Docker:
          ```
          docker build -t hotel-booking-service:v1 .
          docker run -it -d -p 8081:8081 hotel-booking-service:v1
          ```
        * Run:
    * Integration testing:
        * Create booking
            * Method POST
            * Request body: json
            * Return body: UUID of the created booking.
      ```
      curl --location 'http://localhost:8081/api/v1/bookings' \
      --header 'Content-Type: application/json' \
      --data '{
      "roomId": "018e2204-f44e-7952-a810-3a4a4f3078ec",
      "customerId": "018e2210-b91d-7da8-a5b7-f6e0651b1d20",
      "checkInDate": "2024-03-18T14:53:54.123Z",
      "checkOutDate": "2024-03-18T14:53:54.123Z"
      }' 
      ```
        * Update booking:
            * Method PATCH
            * Path param: the UUID returned from API Create Booking above.
            * Request body: json
                * You can cancel the booking and update check-in, checkout-time
                    * Cancel booking: "status" = "CANCELLED"
                    * The date time pattern for check-in, checkout time is: ISO 8601
      ```
      curl --location --request PATCH 'localhost:8081/api/v1/bookings/<UUID from the created booking>' \
      --header 'Content-Type: application/json' \
      --data '{
      "roomId": "018e2204-f44e-7952-a810-3a4a4f3078ec",
      "customerId": "018e2210-b91d-7da8-a5b7-f6e0651b1d20",
      "checkInDate": "2024-03-18T14:53:54.123Z",
      "checkOutDate": "2024-03-18T14:53:54.123Z",
      "status": "CANCELLED"
      }'
      ```
        * Read the detail of booking
            * Method GET
            * Path param: the UUID of the booking that needs to be read.
      ```
      curl --location 'http://localhost:8081/api/v1/bookings/5dc6ab22-a2eb-4ccf-b15c-3d48eb6cc7d2'
      ```
        * Find the hotels at a specific destination
            * Method GET
            * Request query param:
                * Country
                * City
                * Pagination (optional): size, page
      ```
      curl --location 'http://localhost:8081/api/v1/hotels?city=Bangkok&country=Thailand'
      ```