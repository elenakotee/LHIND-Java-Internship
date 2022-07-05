package util;

public final class Queries {

    private Queries() {}

    //Employee Queries
    public static final String FIND_ALL_EMPLOYEES = "SELECT e FROM Employee e";

    //public static final String FIND_EMPLOYEE_BY_FIRST_NAME_METHOD_1 = "SELECT e FROM Employee e WHERE e.firstName = ?1";

    public static final String FIND_EMPLOYEE_BY_FIRST_NAME_METHOD_2 =
            "SELECT e FROM Employee e WHERE e.firstName = :firstName";

    //User queries
    public static final String FIND_ALL_USERS = "SELECT e FROM User e";

    public static final String FIND_USER_BY_USERNAME = "SELECT e FROM User e WHERE e.username = :username";

    public static final String FIND_USER_BY_ROLE = "SELECT e FROM User e WHERE e.role = :role";

    //Booking Queries
    public static final String FIND_ALL_BOOKINGS = "SELECT e FROM Booking e";

    //Flight Queries
    public static final String FIND_ALL_FLIGHTS =
            "SELECT e FROM Flight e";
    public static final String FIND_FLIGHT_BY_ORIGIN =
            "SELECT e FROM Flight e WHERE e.origin = :origin";
    public static final String FIND_FLIGHT_BY_DESTINATION =
            "SELECT e FROM Flight e WHERE e.destination = :destination";
    public static final String FIND_FLIGHT_BY_AIRLINE =
            "SELECT e FROM Flight e WHERE e.airline = :airline";
    public static final String FIND_FLIGHT_BY_FLIGHT_NUMBER =
            "SELECT e FROM Flight e WHERE e.flight_number = :flight_number";
    public static final String FIND_FLIGHT_BY_DEPARTURE_DATE =
            "SELECT e FROM Flight e WHERE e.departure_date = :departure_date";
    public static final String FIND_FLIGHT_BY_ARRIVAL_DATE =
            "SELECT e FROM Flight e WHERE e.arrival_date = :arrival_date";

    //UserDetails Queries
    public static final String FIND_ALL_USER_DETAILS =
            "SELECT e FROM UserDetails e";
    public static final String FIND_USER_DETAILS_BY_FIRST_NAME =
            "SELECT e FROM UserDetails e WHERE e.first_name = :first_name";
    public static final String FIND_USER_DETAILS_BY_LAST_NAME =
            "SELECT e FROM UserDetails e WHERE e.last_name = :last_name";
    public static final String FIND_USER_DETAILS_BY_EMAIL =
            "SELECT e FROM UserDetails e WHERE e.email = :email";
    public static final String FIND_USER_DETAILS_BY_PHONE_NUMBER =
            "SELECT e FROM UserDetails e WHERE e.phone_number = :phone_number";

}
