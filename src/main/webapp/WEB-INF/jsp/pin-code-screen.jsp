<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <script src="/js/keyboard-component.js"></script>
    <script src="/js/keyboard.js"></script>
    <script src="/js/atm-components-initializer.js"></script>

</head>
<body>
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>

    <div class="container atm-keyboard-group-container mt-4">
        <div class="container">
          <form action="/card/pin" method="POST" class="atm-auth-form">
            <div class="form-group row">
              <label for="pinCode" class="col-form-label">Card Number</label>
              <div class="col-sm-2">
                <div class="input-group">
                  <input class="form-control atm-keyboard-input atm-card-pin" type="password"
                         maxlength='4' name="pinCode" id="pinCode">
                  <span class="input-group-btn">
                    <button class="btn btn-secondary clear-atm-input" type="button" for="pinCode">Clear</button>
                  </span>
                </div>
              </div>
              <button type="submit" class="btn btn-success atm-send-btn">Send</button>
            </div>
          </form>
        </div>

        <div class="container col-sm-10 atm-keyboard-component"></div>
        <div class="col-sm-4">
            <button type="button" class="btn btn-warning atm-cancel-btn float-right mt-3 w-75">Cancel</button>
        </div>
    </div>
</body>
</html>

