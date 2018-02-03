var WEBCONTENT = window.NameSpace || {};

(function () {

    WEBCONTENT.return = function () {
        var webmId = $("#webmId").val();
        var webmName = $("#webmName").val();
        $("#updateId").val("");
        window.location.href = "/webmanager/?id="+webmId;
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

        if(content.length>3000){
            alert("超过限制3000字符了");
            return;
        }

        var webContentId = $("#webContentId").val();
        var params = {webmid:webmid,menuid:menuId,content:content,title:title,webContentId:webContentId};
        DEFAULT.Ajax("/webmanager/addOrUpdateContent",params,false,function (res) {
            alert(res.msg);
            if (res.result == true) {
                var menuId = $("#menuId").val();
                $("#updateId").val("");
                window.location.href = "/webmanager/?id="+menuId;
            }
        });
    };

})();