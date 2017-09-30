<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <script src="/js/basic.js" defer></script>
    <script src="/js/basic-navigation.js" defer></script>
</head>
<body>
    <div class='container col-sm-4'>
        <h3>Withdrawal completed successfully.</h3>
        <div class= "row mt-5">
            <table class="table mt-10">
                <tbody>
                    <tr>
                        <td>Card number:</td>
                        <td>
                            <div class="mr-2">${cardNumber}</div>
                        </td>
                    </tr>
                    <tr>
                        <td>Operation time:</td>
                        <td>${operationTime}</td>
                    </tr>
                    <tr>
                        <td>Withdrawal amount:</td>
                        <td>
                            <div class="row">
                                <div class="mr-2">${withdrawalAmount}</div>
                                <div>${currency}</div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>Current balance:</td>
                        <td>
                            <div class="row">
                                <div class="mr-2">${currentBalance}</div>
                                <div>${currency}</div>
                            </div>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="col-lg-12">
            <div class="row mt-10">
                <div class="col-md-offset-3 col-md-5">
                    <button type="button" class="btn btn-info float-right atm-back-button">Back</button>
                </div>
                <div class="col-md-offset-3 col-md-5">
                    <button type="button" class="btn btn-warning btn-xs atm-exit-button">Exit</button>
                </div>
            </div>
        </div>
    </div>
</body>

</html>