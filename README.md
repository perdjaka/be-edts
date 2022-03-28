# Database Structure & Config
<hr/>

#### DB Structure

ERD as below 

![image](https://user-images.githubusercontent.com/1299748/160324212-fc1d0a9e-332c-4dbf-82d8-989aedbfcde1.png)

One grade can have many employees, so the table consist of __grade__ and __employee__ with **_one-to-many_** mapping. 

#### DB Config

current application db config was on _/src/main/resources/application.properties_ 
 
    spring.datasource.url=[your jdbc url]
    spring.datasource.username=[your db username]
    spring.datasource.password=[your db password]

# API Documentation
</hr>

#### 1. Grade API

##### 1.1 Get Grade
###### 1.1.1 Function Name
	/grade
###### 1.1.2 Request Method
	GET
###### 1.1.3 Api Request and Response Fields
Input Payload :
	
	None

Output Payload :
     
    [
	    {
	        "code": "1",
	        "name": "Manager",
	        "rate": 0.10
	    },
	    {
	        "code": "2",
	        "name": "Supervisor",
	        "rate": 0.06
	    },
	    {
	        "code": "3",
	        "name": "Staff",
	        "rate": 0.03
	    }
    ]
    
##### 1.2 Create Grade
###### 1.2.1 Function Name
	/grade/add
###### 1.2.2 Request Method
	POST
###### 1.2.3 Api Request and Response Fields
Input Payload :
	
	{
	    "code":"4",
	    "name":"Director",
	    "rate":0.2
	}

Output Payload :
     
    {
	    "code": "4",
	    "name": "Director",
	    "rate":0.2
	}
   
##### 1.3 Update Grade
###### 1.3.1 Function Name
	/grade/update/:code
###### 1.3.2 Request Method
	PUT
###### 1.3.3 Api Request and Response Fields
Input Payload :
	
	{
	    "name":"Director-edit",
	    "rate":0.3
	}

Output Payload :
     
    {
	    "code": "4",
	    "name": "Director-edit",
	    "rate": 0.3
	 }
   
##### 1.4 Create Grade
###### 1.4.1 Function Name
	/grade/delete/:code
###### 1.4.2 Request Method
	DELETE
###### 1.4.3 Api Request and Response Fields
Input Payload :
	
	none

Output Payload :
     
    none
    
<hr/>

#### 2. Employee API

##### 2.1 Get Employee
###### 2.1.1 Function Name
	/employee
###### 2.1.2 Request Method
	GET
###### 2.1.3 Api Request and Response Fields
Input Payload :
	
	None

Output Payload :
     
    [
	    {
	        "id": 29,
	        "name": "jonah bluesky",
	        "salary": 7563000.00,
	        "bonus": 8319300.00,
	        "grade": {
	            "code": "1",
	            "name": "Manager",
	            "rate": 0.10
	        }
	    },
	    {
	        "id": 30,
	        "name": "stevenson black",
	        "salary": 5124000.00,
	        "bonus": 5431440.00,
	        "grade": {
	            "code": "2",
	            "name": "Supervisor",
	            "rate": 0.06
	        }
	    },
	    {
	        "id": 31,
	        "name": "susan random",
	        "salary": 3980000.00,
	        "bonus": 4099400.00,
	        "grade": {
	            "code": "3",
	            "name": "Staff",
	            "rate": 0.03
	        }
	    }
	 ]
    
##### 2.2 Create Employee
###### 2.2.1 Function Name
	grade/:code/employee/add
###### 2.2.2 Request Method
	POST
###### 2.2.3 Api Request and Response Fields
Input Payload :
	
	{
	    "name":"fandi",
	    "salary":1000000
	}

Output Payload :
     
    {
	    "id": 61,
	    "name": "fandi",
	    "salary": 1000000,
	    "bonus": 1030000,
	    "grade": {
	        "code": "3",
	        "name": "Staff",
	        "rate": 0.03
	    }
	 }
   
##### 2.3 Update Employee
###### 2.3.1 Function Name
	/grade/:code/employee/update
###### 2.3.2 Request Method
	PUT
###### 2.3.3 Api Request and Response Fields
Input Payload :
	
	{
        "id": 61,
        "name": "fandi fadillah",
        "salary": 2000000
    }

Output Payload :
     
    {
	    "id": 61,
	    "name": "fandi fadillah",
	    "salary": 2000000,
	    "bonus": 2200000,
	    "grade": {
	        "code": "1",
	        "name": "Manager",
	        "rate": 0.10
	    }
	 }
   
##### 2.4 Create Employee
###### 2.4.1 Function Name
	/employee/delete/:code
###### 2.4.2 Request Method
	DELETE
###### 2.4.3 Api Request and Response Fields
Input Payload :
	
	none

Output Payload :
     
    none
