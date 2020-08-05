# Encryption-Decryption
Purpose

The program encrypts a message via 2 different algorithms.

I wrote the code in IntelliJ IDEA and used the "Edit Configurations" option to write the arguments.
In IntelliJ IDEA under RUN > Edit Configurations > Program arguments
add the following argument:

<b>"-alg shift -mode enc -key 5 -in ../Files_to_be_read/road_to_treasure.txt -out ../Files_to_be_read/output.txt"</b>

This argument reads the file road_to_treasure.txt, encrypts it using the shift algorithm and writes it into the output.txt file. 

<b>-alg</b>    choose between the Unicode algorithm (https://www.utf8-zeichentabelle.de/unicode-utf8-table.pl?names=-&unicodeinhtml=hex) or the shift algorithm <br/>
<b>-key</b>    shifts the letter for the given number <br/>
                        shift algorithm W -> b, y -> d <br/>
                        unicode W -> \, y -> ~  <br/>
<b>-in</b>     the path of the input file  <br/>
<b>-out</b>    the path of the output file <br/>
<b>-mode</b>   2 modes enc for encryption, dec for decryption <br/>
<b>-data</b>   if not using file as input, you can directly write the message using this command
