var WEBCONTENT = window.NameSpace || {};

(function () {

    WEBCONTENT.return = function () {
        var menuId = $("#menuId").val();
        var menuName = $("#menuName").val();
        window.location.href = "/webmanager/?id="+menuId+"&name="+menuName;
    };

})();