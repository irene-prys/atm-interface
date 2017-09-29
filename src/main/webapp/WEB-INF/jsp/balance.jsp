<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
</head>
<body>
    <div class='container col-sm-4'>
        <div class= "row mt-5">
            <table class="table mt-10">
                <tbody>
                    <tr>
                        <td>Balance:</td>
                        <td>
                            <div class="row">
                                <div class="mr-2">${cardBalance}</div>
                                <div>${balanceCurrency}</div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Current time:</td>
                        <td>${operationTime}</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="row">
        </div>

    </div>



</body>
</html>
