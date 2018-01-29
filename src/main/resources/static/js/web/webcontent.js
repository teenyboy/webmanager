var WEBCONTENT = window.NameSpace || {};

(function () {

    WEBCONTENT.return = function () {
        var webmId = $("#webmId").val();
        var webmName = $("#webmName").val();
        $("#updateId").val("");
        window.location.href = "/webmanager/?id="+webmId+"&name="+webmName;
    };

    /**
     * 获取富文本框内容
     */
    WEBCONTENT.getContent = function () {
        alert( UE.getEditor('inputContent').getContent());
    };

    /**
     * 保存更新富文本内容
     */
    WEBCONTENT.addAndUpdateContent = function () {
        var webmid = $("#webmid").val();
        var menuId = $("#menuId").val();
        var title = $("#inputTitle").val();
        var content = UE.getEditor('inputContent').getContent();
        var params = {webmid:webmid,menuid:menuId,content:content,title:title};
        DEFAULT.Ajax("/webmanager/addOrUpdateContent",params,false,function (res) {
            alert(res.msg);
            if (res.result == true) {
                var webmId = $("#webmId").val();
                var webmName = $("#webmName").val();
                $("#updateId").val("");
                window.location.href = "/webmanager/?id="+webmId+"&name="+webmName;
            }
        });
    };

})();