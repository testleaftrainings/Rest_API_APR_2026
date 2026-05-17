Feature: Validate the CRUD operation of the incident table servicenow api

Background:
Given set baseuri as "https://dev373619.service-now.com"
And set basepath as "/api/now/table"
And basic auth username as "admin" and password as "7d3iJH=K$jYf"
And path param variable name as "tableName" and value as "incident"

Scenario: Validate user should able to get all records from table api
When hit get method with url "/{tableName}"
Then response validation
| statusCode | statusMessage | responseFormate |
| 200        | OK            | JSON            |