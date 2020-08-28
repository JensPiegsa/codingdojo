# Bank OCR Kata #

## User Story 4 ##

It turns out that often when a number comes back as ERR or
ILL it is because the scanner has failed to pick up on one pipe
or underscore for one of the figures. For example

```
    _  _  _  _  _  _     _ 
|_||_|| || ||_   |  |  ||_ 
  | _||_||_||_|  |  |  | _|
```

The 9 could be an 8 if the scanner had missed one |. Or the 0
could be an 8. Or the 1 could be a 7. The 5 could be a 9 or 6. So
your next task is to look at numbers that have come back as
ERR or ILL, and try to guess what they should be, by adding
or removing just one pipe or underscore. If there is only one
possible number with a valid checksum, then use that. If there
are several options, the status should be AMB. If you still can’t
work out what it should be, the status should be reported ILL.

* see also: [codingdojo.org/kata/BankOCR](https://codingdojo.org/kata/BankOCR/)