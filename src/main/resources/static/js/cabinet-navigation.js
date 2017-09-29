let initNavigation = function () {
    let basicFunctionality = basicAtmFunctions();

    initButtons();

    function initButtons() {
        initBalanceBtn();
        initWithdrawBtn();
        initExitBtn();
    }

    function initBalanceBtn() {
        let balanceBtn = document.body.querySelector(".balance-button");
        balanceBtn.onclick = function() {
            location.href='/cabinet/balance';
        };
    }

    function initWithdrawBtn() {
        let withdrawBtn = document.body.querySelector(".withdraw-button");
        withdrawBtn.onclick = function() {
            location.href='/cabinet/withdraw';
        };
    }

    function initExitBtn() {
        let exitBtn = document.body.querySelector(".exit-button");
        exitBtn.onclick = function() {
            basicFunctionality.clearHistoryAndGoToIndex();
        };
    }
};

initNavigation();