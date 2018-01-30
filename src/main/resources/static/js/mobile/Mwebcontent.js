var M_WEBCONTENT = window.NameSpace || {};

(function () {

    /**
     * 返回内容
     */
    M_WEBCONTENT.return = function () {
        var webMid = $("#webMid").val();
        window.location.href = "/m/webmanager/?id="+webMid;
    }
})();