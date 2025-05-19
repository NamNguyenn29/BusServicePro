BookingDAO :
    currentTripbookkings là lưu những booking của trip hiện tại để check xem bus có full chỗ chưa
    bookings là chữa hết danh sách các booking
    tạo booking check xem booking đó có hợp lệ hay không , sau đó lưu xuống db
    gett bookings by userID : lấy những booking cuả user đó
    getBooking by tripID:
    getAll bookibg : lấy hết danh sách booking trong  db

BusDAO
    lấy thông tin xe bus theo ID : getBusByID
    lấy tất cả xe bus
    addBus vào db
    lấy danh sách xe bus theo tuyến đường
RouteDAO
    getAllRoute lấy hết danh sách route ( cũng chính là searchAllRoute
        getStopForRoute : map danh sách stop vào route (sử dụng cho getAllRoute)
    addRoute vào db
    getRouteByID

    search Route by stopID *
RouteStop (vì mối quan hệ nhiều nhiều nên cần lớp RouteStop để nối Route và Stop)
    addRouteStop vào db
    getstopByRouteId trả  về danhh sách các stop gắn với Route đó
    deleteStopsFromRoute
StopDAO
    getStopByID
    getAllStop
    addStop vào db
    search Stop by name
    getRoutesForStop search stopID trả về Route chứa Stop này

    getStop by route ID *
    delete,update
StoptimeDAO
    getStoptimeByID
    add Stoptime
    addStopTime to trip dùng để map stopStime vào trip tương ứng
Trip
    getStopWithStopTimes :    lấy ra trip hoàn chỉnh
    addTrip : add trip mới vào db
    addStop time to trip
      remove,update

    searchTrip *
User
    login
    register
    getUserByID


AddminDAO
    login và register, get admin by id

mức độ cao đến thấp : booking - trip - bus - route - stop - stopTime

RouteController
Đặt fx:controller="com.example.Controller.RouteController" ở phần đầu của file.

Có ListView với fx:id="routeListView"

Có nút bấm gọi onAction="#handleButtonClick"
<AnchorPane fx:controller="com.example.Controller.RouteController"
            xmlns:fx="http://javafx.com/fxml">
    <children>
        <TextField fx:id="stopIDField" layoutX="50" layoutY="20" promptText="Nhập Stop ID" />
        <Button text="Tìm theo Stop ID" layoutX="250" layoutY="20" onAction="#handleSearchByStopID" />
        <Button text="Hiện tất cả tuyến đường" layoutX="400" layoutY="20" onAction="#handleShowAllRoutes" />
        <ListView fx:id="routeListView" layoutX="50" layoutY="70" prefWidth="500" prefHeight="400" />
    </children>
</AnchorPane>