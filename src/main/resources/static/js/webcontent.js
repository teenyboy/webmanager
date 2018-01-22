var WEBCONTENT = window.NameSpace || {};

(function () {

    WEBCONTENT.return = function () {
        var menuId = $("#menuId").val();
        var menuName = $("#menuName").val();
        window.location.href = "/webmanager/?id="+menuId+"&name="+menuName;
    };

    /**
     * 获取富文本框内容
     */
    WEBCONTENT.getContent = function () {
        alert( UE.getEditor('inputContent').getContent());
    }
    
})();