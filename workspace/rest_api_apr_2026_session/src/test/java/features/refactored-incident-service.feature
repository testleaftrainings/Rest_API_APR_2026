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

Scenario: Validate user should filter the response filed using sysparams_fields query param
Given query param key as "sysparm_fields" and value as "sys_id,number,category"
When hit get method with url "/{tableName}"
Then response validation
| statusCode | statusMessage | responseFormate |
| 200        | OK            | JSON            |

Scenario: Validate user should able to get all records from table api in XML response formate
Given header key as "Accept" and value as "application/xml"
When hit get method with url "/{tableName}"
Then response validation
| statusCode | statusMessage | responseFormate |
| 200        | OK            | XML             |


Scenario Outline: Validate user should able to create the multiple records using POST methods
Given header key as "Content-Type" and value as "application/json"
And set value of the shortDescription key as "<shortDescription>"
And set value of the description key as "<description>"
When hit post metho with url "/{tableName}"
Then response validation
| statusCode | statusMessage | responseFormate |
| 201        | Created       | JSON            |

Examples:
| shortDescription | description    |
| RESTAPIJAN2026   | Jan Month Data |
| RESTAPIFEB2026   | Feb Month Data |
| RESTAPIMAR2026   | Mar Month Data |
