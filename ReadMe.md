## Task
Create a mortgage loan calculator

## Inputs:
* the required loan amount
* term of loan (time needed for returning the money) (year and month)
* yearly percentage
* type of payment (linear, annuity)
* the choice to delay payment by date and percentage

## Use:
* this
* *(extends)*
* *(super)*
* encapsulation
* at least 2 packages to contain project classes
* .jar file to compile

## Outputs:
* how much money should be payed each month (general monthly should also be seperated to interest and loan) and how much is yet to be repayed
* the choice to filter months by intervals
* the choice to print table results to file
* the choice to see a line chart which compared linear and annuity payment types for each month


To compile with .jar, open Loan.jar in terminal and the write:
```
java --module-path "C:\Program Files\Java\openjfx-17.0.2_windows-x64_bin-sdk\javafx-sdk-17.0.2\lib" --add-modules=javafx.controls,javafx.fxml -jar Loan.jar
```
C:\Program Files\Java\openjfx-17.0.2_windows-x64_bin-sdk\javafx-sdk-17.0.2\lib - my path for javafx lib

### Example
![Untitled picture](https://user-images.githubusercontent.com/65849358/163430061-30e6bf74-1d31-4747-bbc3-fe1c0d1b2b72.png)
