Feature: Validate the CRUD operation of the incident table servicenow api

Scenario: Validate user should able to get all records from table api
Given set baseuri as "https://dev373619.service-now.com"
And set basepath as "/api/now/table"
And basic auth username as "admin" and password as "7d3iJH=K$jYf"
And path param variable name as "tableName" and value as "incident"
When hit get method with url "/{tableName}"
Then status code is 200
And status message is "OK"
And response content fromate is "JSON"

Scenario: Validate user should filter the response filed using sysparams_fields query param
Given set baseuri as "https://dev373619.service-now.com"
And set basepath as "/api/now/table"
And basic auth username as "admin" and password as "7d3iJH=K$jYf"
And path param variable name as "tableName" and value as "incident"
And query param key as "sysparm_fields" and value as "sys_id,number,category"
When hit get method with url "/{tableName}"
Then status code is 200
And status message is "OK"
And response content fromate is "JSON"

Scenario: Validate user should able to get all records from table api in XML response formate
Given set baseuri as "https://dev373619.service-now.com"
And set basepath as "/api/now/table"
And basic auth username as "admin" and password as "7d3iJH=K$jYf"
And path param variable name as "tableName" and value as "incident"
And header key as "Accept" and value as "application/xml"
When hit get method with url "/{tableName}"
Then status code is 200
And status message is "OK"
And response content fromate is "XML"
