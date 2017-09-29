function basicAtmFunctions() {
    return {
        clearHistoryAndGoToIndex: function () {
            this.clearHistory();
            window.location = "/cancel";
        },

        clearHistory: function () {
            for (i = 0; i < window.history.length; i++) {
                history.pushState({}, '');
            }
            history.pushState({}, '', '/');
            window.location.replace(window.location.href);
        }
    };
}