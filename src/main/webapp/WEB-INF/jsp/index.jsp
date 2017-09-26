<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <script src="/js/keyboard-component.js"></script>
    <script src="/js/keyboard.js"></script>

</head>
<body>
    <div class="container atm-keyboard-group-container">
        <div class="container">
          <form>
            <div class="form-group row">
              <label for="inputEmail3" class="col-sm-2 col-form-label">Card Number</label>
              <div class="col-sm-3">
                <div class="input-group">
                  <input class="form-control atm-keyboard-input" type="text" placeholder="1111-1111-1111-1111">
                  <span class="input-group-btn">
                    <button class="btn btn-secondary" type="button">Clear</button>
                  </span>
                </div>
              </div>
              <button type="button" class="btn btn-success">Send</button>
            </div>
          </form>
        </div>

        <div class="container col-sm-8 atm-keyboard-component"></div>
    </div>
</body>
</html>

