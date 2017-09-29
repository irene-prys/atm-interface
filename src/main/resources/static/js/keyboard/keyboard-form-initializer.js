'use-strict'

let applyAtmInputSettings = function () {
    let CARD_NUMBER_SEGMENT_SIZE = 4;
    let basicFunctionality = basicAtmFunctions();

    document.addEventListener("DOMContentLoaded", ready);

    function ready() {
        applySettings();
    }

    function applySettings() {
        applySettingsToCardNumberComponents();
        applySettingsForClearButtons();
        applySettingsToPinCodeComponents();
        applySettingsToCancelButton();
        applyFormSettings();
    }

    function applySettingsToCardNumberComponents() {//todo: move to separate file
        let atmCardNumberComponents = document.body.querySelectorAll('.atm-card-number');
        for(let i = 0; i < atmCardNumberComponents.length; i++) {
            let inputElement = atmCardNumberComponents[i];
            invalidateSendButton(inputElement);
            inputElement.addEventListener('textChanged', function (e) {
                invalidateSendButton(inputElement);
                if(isSeparatorNeeded(inputElement.value.length, inputElement.maxLength)) {
                    inputElement.value += '-';
                }
            });
        }
    }

    function applySettingsToCancelButton() {// todo: rename component? or move to separate file?
        let cancelBtns = document.body.querySelectorAll(".atm-cancel-btn");
        for(let i = 0; i < cancelBtns.length; i++) {
            if(cancelBtns[i]) {
                cancelBtns[i].onclick = function() {
                    basicFunctionality.clearHistoryAndGoToIndex();
                };
            }
        }
    }

    function applySettingsToPinCodeComponents() {// todo: rename component? or move to separate file?
        let atmCardNumberComponents = document.body.querySelectorAll('.atm-card-pin');
        for(let i = 0; i < atmCardNumberComponents.length; i++) {
            let inputElement = atmCardNumberComponents[i];
            invalidateSendButton(inputElement);
            inputElement.addEventListener('textChanged', function (e) {
                invalidateSendButton(inputElement);
            });
        }
    }

    function applySettingsForClearButtons() {// todo: rename component to clear-keyboard-form-input
        let clearButtons = document.body.querySelectorAll('.clear-atm-input');
        for(let i = 0; i < clearButtons.length; i++) {
            clearButtons[i].onclick = function() {
                let inputElement = document.getElementById(clearButtons[i].getAttribute("for"));
                if(inputElement) {
                    inputElement.value = "";
                    invalidateSendButton(inputElement);
                }
            }
        }
    }

    function invalidateSendButton(inputElement) {// todo: rename component to keyboard-group-container and keyboard-form-send-btn
        let keyboardGroupContainer = inputElement.closest(".atm-keyboard-group-container");
        let sendBtnElement = keyboardGroupContainer.querySelector(".atm-send-btn");
        let disable = inputElement.maxLength !== -1 && inputElement.value.length < inputElement.maxLength;// if input is full then disable
        sendBtnElement.disabled = disable;
    }

    function applyFormSettings(){// todo: rename component
        let forms = document.body.querySelectorAll('.atm-auth-form');
        for(let i = 0; i < forms.length; i++) {
            forms[i].onsubmit = function () {
                basicFunctionality.clearHistory();
            };
        }
    }

    function isSeparatorNeeded(currentLength, maxLength) {
        if(maxLength !== -1 && currentLength >= maxLength) {
            return false;
        }

        let countOfSegments = Math.floor(currentLength/CARD_NUMBER_SEGMENT_SIZE);
        let countOfSeparators = currentLength%CARD_NUMBER_SEGMENT_SIZE;
        return countOfSeparators + 1 === countOfSegments;
    }

};

applyAtmInputSettings();