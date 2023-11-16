# salary-survey



## How to start
#### type  in console
```  
mvn spring-boot:run  
```

#### To test filter
```
localhost:8080/salary-survey/survey?size=20&salary=135000&gender=male
```

#### To test column filter
```
localhost:8080/salary-survey/survey?size=20&fields=id,employer,jobTitle&salary=135000&gender=male
```

#### To test search with date range
```
localhost:8080/salary-survey/survey?timestamp=2016-03-21T12:54:00&timestamp=2016-03-21T13:00:00
```

#### To test sorting
```
localhost:8080/salary-survey/survey?sort=yearsAtEmployer,DESC&salary=135000
```


>**It is recommended to import fixed csv data with ids which is attached in the mail.**