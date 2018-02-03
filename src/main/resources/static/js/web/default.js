var DEFAULT = window.NameSpace || {};

(function () {

    DEFAULT.RESPONSE_STATUS_KEY="REQ_FLAG";
    DEFAULT.RESPONSE_MESSAGE_KEY="REQ_MSG";
    DEFAULT.RESPONSE_DATA_KEY="REQ_DATA";

    /***********外框逻辑**********************************************************************************************************/

    DEFAULT.dealFrame =function () {
        DEFAULT.queryMenus();//组装外框下拉栏目
        DEFAULT.user();//填充姓名
    };

    DEFAULT.queryMenus =function () {
        DEFAULT.Ajax("/queryManagerMenus", null, true, function (res) {
            if (res.result == true) {
                if(DEFAULT.isEmptyStr(res.msg)){
                    alert("操作异常，请刷新页面");
                    return;
                }
                var resObj = eval('('+res.msg+')');
                for(var i = 0;i<resObj.length;i++){
                    $("#managerMenus").append('<li><a href="/webmanager/?id='+resObj[i].id+'"><i class="fa fa-circle-o"></i>'+resObj[i].name+'</a></li>');
                }
            } else {
                alert(res.msg);
            }
        });
    };

    DEFAULT.user =function () {
        $("#userNameSmall").text("admin");
        $("#userNameDropDown").text("admin");
    };

    DEFAULT.toMenuContent = function (menuId) {
        console.log(menuId)
    };


    /***********外框逻辑**********************************************************************************************************/



    /***********公共组件**********************************************************************************************************/
    DEFAULT.isEmptyStr = function (str) {
        if(str == undefined || str == null || str == "" ||str.length === 0){
            return true;
        }else {
            return false;
        }
    };

    /**
     * 从html表格属性中读取每一行数据的配置
     * @param tableSelector 表格的选择器
     * @returns {Array}
     */
    DEFAULT.parseColumns = function(tableSelector) {
        if ($(tableSelector).length < 1) {
            throw new Error('表格'+tableSelector+'不存在!');
        }

        var cols = [];
        var ths = $(tableSelector).find("th");
        for (var i = 0; i < ths.length; i++) {
            var col = {};
            var func = $(ths[i]).attr("data-formatter");
            col.data = $(ths[i]).attr("data-field");
            if (func != undefined) {
                col.render = eval(DEFAULT.strFormat("typeof {func} == 'function' ? {func} :  null", {func: func}));
            }
            if (typeof $(ths[i]).attr("titled") != "undefined") {
                var length = $(ths[i]).attr("titled");
                var funcBody = DEFAULT.strFormat("col.render = function(data, type, full, meta) {return DEFAULT.titleFormatter(data, {length});};", {length: length ? length : 0});
                if (typeof col.render == 'function') {
                    funcBody = DEFAULT.strFormat("col.render = function(data, type, full, meta) {return DEFAULT.titleFormatter({func}(data, type, full, meta), {length});};", {func: func, length: length ? length : 0});
                }
                eval(funcBody);
            }
            cols.push(col);
        }
        return cols;
    };

    /**
     * title格式化
     * @param
     * return string
     */
    DEFAULT.titleFormatter = function(data, length) {
        if (length && data && data.length > length || !length && data) {
            return '<span title="' + data.replaceAll('"', "&quot;") + '">' + data + '</span>'
        }
        return data;
    };

    /**
     * 查询方法，返回数据要求为JSON格式数据，具体数据格式如下：
     * {
     *      "recordsFiltered": 100, // 总的记录数
     *      "recordsTotal": 100, // 总的记录数
     *      "data":[    // 当前页的数据
     *          {"id": 1, "name": LiLi},
     *          {"id": 2, "name": LiLei}
     *      ]
     * }
     *
     * 数据列表的渲染的方式使用dataTable，渲染配置通过读取html页面对应的table元素获取[支持天网2.0版本的样式]
     *
     * @param url string; 必填; 请求url
     * @param data object; 必填; 请求的参数
     * @param tableSelector string; 必填; 查询结果的展示位置
     * @param error function; 非必填; 查询异常回调方法，默认回调方法提示“系统繁忙”
     */
    DEFAULT.ajaxQueryTable = function(url, data, tableSelector, error, option, eventHandlers) {
        if(typeof (DEFAULT.tableMap) == 'undefined'){
            DEFAULT.tableMap = new Object();
        }
        DEFAULT.tableMap[tableSelector+"_data"] = data;
        if (!$(tableSelector).attr("initTime")) {
            $(tableSelector).attr("initTime", new Date().getTime());
            // 解析列的信息
            var columns = DEFAULT.parseColumns(tableSelector);
            // 默认dataTable配置
            var defaultOption = {
                "oLanguage": {
                    "sLengthMenu": "每页显示 _MENU_ 条记录",
                    "sZeroRecords": "抱歉， 没有找到",
                    "sInfo": "从 _START_ 到 _END_ 共 _TOTAL_ 条数据",
                    "sInfoEmpty": "没有数据",
                    "sInfoFiltered": "(从 _MAX_ 条数据中检索)",
                    "oPaginate": {
                        "sFirst": "首页",
                        "sPrevious": "前一页",
                        "sNext": "后一页",
                        "sLast": "尾页"
                    },
                    "sZeroRecords": "没有检索到数据"
                },
                "autoWidth": false,
                "serverSide": true,
                "ajax": {
                    "url": url,
                    "method": "post",
                    "beforeSend": function(e){},
                    "data": function (d) {
                        var data = DEFAULT.tableMap[tableSelector+"_data"];
                        var p = $.extend({}, data ? data : {}, {draw: d.draw, pageSize: d.length, startIndex: d.start});
                        for (key in p) {
                            if (typeof p[key] === "undefined" || (typeof p[key] === "string" && DEFAULT.isEmptyStr(p[key]))) {
                                delete p[key];
                            }
                        }
                        return p;
                    }
                },
                "columns": columns,
                "dom": '<"top"i>rt<"bottom"flp><"clear">',
                "searching" :  false,
                "ordering": false
            };

            if (typeof option == "object") {
                defaultOption = $.extend(defaultOption, option);
            }

            var defaultEventHandlers = {
                "xhr.dt": typeof success === "function" ? success : function (e, settings, json, xhr) {
                },
                "length.dt": function ( e, settings, len ) {
                    DEFAULT.tableMap[tableSelector].api().page( 'first' ).draw( true );
                }
            };
            if (typeof eventHandlers == "object") {
                defaultEventHandlers = $.extend(defaultEventHandlers, eventHandlers);
            }

            var t = $(tableSelector);
            for (var key in defaultEventHandlers) {
                t = t.on(key, defaultEventHandlers[key]);
            }
            DEFAULT.tableMap[tableSelector] = t.dataTable(defaultOption);
        } else {
            DEFAULT.tableMap[tableSelector].api().ajax.url( url ).load();
        }
        $(tableSelector).show();
    };


    /**
     * 使用dataTables插件展示Json数据
     * @param domId DOM节点ID
     * @param params 参数(详细查看dataTables配置)
     */
    DEFAULT.dataTableForJson = function(domId,dataSet,columns,params) {
        //默认配置
        var def_opts = {
            oLanguage : {
                "sLengthMenu" : "每页显示 _MENU_ 条记录",
                "sZeroRecords" : "抱歉， 没有找到要查询的数据！",
                "sInfo" : "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
                "sInfoEmpty" : "没有数据",
                "sSearch": "全局搜索:",
                "sInfoFiltered" : "(从 _MAX_ 条数据中检索)",
                "oPaginate": {
                    "sFirst" : "第一页",
                    "sPrevious" : "上一页",
                    "sNext" : "下一页",
                    "sLast" : "最后一页"
                }
            },

            renderer : BASE.TABLE.sPaginationType,
            sDom : BASE.TABLE.sDom,
            bProcessing : false,
            bServerSide :false,
            bStateSave  : true,
            bFilter : true,
            bSort : true,
            aLengthMenu : [5, 10, 25],
            iDisplayLength : 5,
            data: dataSet,
            columns: columns
        };
        var options = $.extend(true, {}, def_opts, params || {});
        var table = $('#'+domId).dataTable(options);
        return table;
    };

    /**
     * 字符串格式化
     * @format 格式化
     * @param args
     * @returns {StringUtils.function}
     */
    DEFAULT.strFormat = function(format, args) {
        var result = format;
        if (arguments.length > 0) {
            if (arguments.length == 2 && typeof (args) == "object") {
                for (var key in args) {
                    if(args[key]!=undefined){
                        var reg = new RegExp("({" + key + "})", "g");
                        result = result.replace(reg, args[key]);
                    }
                }
            } else {
                for (var i = 0; i < arguments.length; i++) {
                    if (arguments[i] != undefined) {
                        //var reg = new RegExp("({[" + i + "]})", "g");//这个在索引大于9时会有问题，谢谢何以笙箫的指出

                        var reg= new RegExp("({)" + i + "(})", "g");
                        result = result.replace(reg, arguments[i]);
                    }
                }
            }
        }
        return result;
    }

    /**
     * ajax含遮罩请求
     * @param url
     * @param data
     * @param isMask
     * @param suc
     * @param err
     * @constructor
     */
    DEFAULT.Ajax = function (url, data, isMask, suc, err) {
        if (isMask) {
            $("#defaultBody").showLoading();
        }
        $.ajax(
            {
                url: url,
                type: "POST",
                data: data,
                dataType: "json",
                success: function (response) {
                    $('#defaultBody').hideLoading();
                    if(response[DEFAULT.RESPONSE_STATUS_KEY] == false){//出错了
                        alert(response[DEFAULT.RESPONSE_MESSAGE_KEY]);
                        return;
                    }
                    if(typeof(suc)=="function"){
                        suc(response);
                    } else {
                        alert(response[DEFAULT.RESPONSE_MESSAGE_KEY]);
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    if (XMLHttpRequest.status == 0) {
                        location.reload();
                    }
                    $('#defaultBody').hideLoading();
                    var responseText = XMLHttpRequest.responseText;
                    var response = eval("(" + responseText + ")");
                    var msg = response.msg;
                    if (msg == undefined){
                        if(typeof(err)=="function"){
                            err(responseText);
                        } else {
                            alert("操作失败");
                        }
                    } else {
                        alert(msg);
                    }
                }
            }
        );
    };

    /**
     * 时间格式化
     * @param time
     * @param formatStyle
     * @returns {*}
     */
    DEFAULT.formatTime = function (time,formatStyle) {
        if(!DEFAULT.isEmptyStr(time)&&!DEFAULT.isEmptyStr(formatStyle)){
            return moment(time).format(formatStyle);
        }else{
            return time;
        }
    };

    /**
     * 操作失败弹窗
     * @param msg
     */
    DEFAULT.errorAlert = function (msg) {
        alert(msg+"操作失败，请刷新");
    };

    /***********公共组件**********************************************************************************************************/
})();