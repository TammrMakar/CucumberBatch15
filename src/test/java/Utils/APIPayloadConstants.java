package Utils;

import org.json.JSONObject;

public class APIPayloadConstants {
    //we will pass the body in multiple formats, for this we have created this class

    public static String createEmployeePayload(){
        String createEmployeePayload = "{\n" +
                "  \"emp_firstname\": \"nelena\",\n" +
                "  \"emp_lastname\": \"faria\",\n" +
                "  \"emp_middle_name\": \"ms\",\n" +
                "  \"emp_gender\": \"F\",\n" +
                "  \"emp_birthday\": \"2012-05-20\",\n" +
                "  \"emp_status\": \"Confirmed\",\n" +
                "  \"emp_job_title\": \"Engineer\"\n" +
                "}";
        return createEmployeePayload;
    }

    public static String createEmployeePayloadJson(){
        JSONObject obj = new JSONObject();
        obj.put("emp_firstname","nelena");
        obj.put("emp_lastname","faria");
        obj.put("emp_middle_name","ms");
        obj.put("emp_gender","F");
        obj.put("emp_birthday","2012-05-20");
        obj.put("emp_status","Confirmed");
        obj.put("emp_job_title","Engineer");
        return obj.toString();
    }
}
