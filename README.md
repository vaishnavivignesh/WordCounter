# WordCounter
Library to add and count 


This library accept multiple strings in both list and string format(separated by spaces).


Sample Request and Response
---------------------------

Request

curl --location 'http://localhost:8080/addword' \
--header 'Content-Type: application/json' \
--data '{
    "value" : "How are you",
    "valueList" : ["Edinburgh", "Glasgow"]
}'


search a word returns the count of the word if available in the list of words  added.

curl --location 'http://localhost:8080/word/edinburgh'
