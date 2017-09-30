<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <script src="/js/basic.js" defer></script>
    <script src="/js/keyboard/keyboard-component.js" defer></script>
    <script src="/js/keyboard/keyboard.js" defer></script>
    <script src="/js/keyboard/keyboard-form-initializer.js" defer></script>
    <script src="/js/basic-navigation.js" defer></script>

</head>
<body>
    <c:if test="${not empty error}">
        <div class="alert alert-danger">${error}</div>
    </c:if>
    <div class="container keyboard-group-container mt-4">
        <div class="container">
          <form action="/withdraw/sum" method="POST" class="atm-withdraw-form">
            <div class="form-group row">
              <label for="withdrawAmount" class="col-form-label">Amount to withdraw</label>
              <div class="col-sm-2">
                <div class="input-group">
                  <input class="form-control atm-keyboard-input atm-card-number" type="text"
                         minlength='1' maxlength='5'
                         name="withdrawAmount"
                         id="withdrawAmount">
                  <span class="input-group-btn">
                    <button class="btn btn-secondary clear-keyboard-form-input" type="button" for="withdrawAmount">Clear</button>
                  </span>
                </div>
              </div>
              <button type="submit" class="btn btn-success keyboard-form-send-btn">Send</button>
            </div>
          </form>
        </div>

        <div class="container col-sm-9 atm-keyboard-component"></div>
        <div class="col-lg-7 mt-4">
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

