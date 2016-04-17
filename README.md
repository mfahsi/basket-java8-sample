# basket-java8-sample

##The Requirement##
program that takes a basket of items and outputs its total cost, items can be Bananas, Oranges, Apples, Lemons, Peaches

##Specifications##
In this version the price list is static, can not be changed.
Design allows easily to externalise the price list or to implement a new source of prices.

##Environment##
- Java 1.8
- Maven 3.3.3

##Running instructions ##
the jar executable, run with basket content as main arguments (valid main arguments are :  Banana, Orange, Apple, Lemon, Peache). 
for example :
\basketpricer\target>java -jar basketpricer-1.0-SNAPSHOT.jar Apple Orange Orange Banana 

prices are :
+ Banana : 20p
+ Orange : 80p per unit, two for 1 £
+ Apple : 30p
+ Lemon : 25p
+ Peach : 50p


