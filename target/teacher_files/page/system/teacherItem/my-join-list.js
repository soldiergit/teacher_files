layui.config({
    base: '/teacher_files_war/static/layui_exts/'
}).extend({
    excel: 'excel'
}).use(['form','layer','table','excel','laytpl','util'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        excel = layui.excel,
        laytpl = layui.laytpl,
        util = layui.util,
        table = layui.table;

    var teacherId = window.sessionStorage.getItem("teacherId");
    //信息列表
    var tableIns = table.render({
        elem: '#infoList',//数据表格id
        url: '/teacher_files_war/biz/teacherItem_findByMember.action?memberId='+teacherId,//数据接口
        page : true,//开启分页
        height : "full-125",//容器高度
        limits : [10,15,20,25],
        limit : 20,
        toolbar: '#toolbar',//头部工具栏
        title: '我参与的项目',//用于导出
        id : "infoListTable",//给它一个id，用于下面更新表单内容
        cols : [[//表头
            {type: "checkbox", fixed:"left", width:50},
            // {field: 'itemId', title: 'Id', align:'center'},
            {field: 'itemName', title: '项目名称', minWidth:250, align:"center"},
            {field: 'personName', title: '主持人', minWidth:100, align:'center',templet:function(d){
                    return d.itemPerson.teacherName;
                }},
            {field: 'itemType', title: '项目类型', minWidth:100, align:'center',templet:function(d){
                    if (d.itemType=='1') return '教改类';
                    if (d.itemType=='2') return '科研类';
                }},
            {field: 'itemCategory', title: '项目类别', minWidth:200, align:'center',templet:function(d){
                    return d.itemCategory.title;
                }},
            {field: 'createTime', title: '立项时间', minWidth:100, align:'center',templet: function (d) {
                    return util.toDateString(d.createTime, "yyyy年")
                }},
            {field: 'itemMoney', title: '项目经费', minWidth:100, align:'center', templet: function (d) {
                    return d.itemMoney+"/万元"
                }},
            {field: 'itemLevel', title: '项目级别', minWidth:100, align:'center',templet:function(d){
                    return d.itemLevel.title;
                }},
            // {field: 'contractNumber', title: '合同编号', minWidth:100, align:'center'},
            {field: 'memberName', title: '成员', minWidth:100, align:'center'},
            // {field: 'startTime', title: '起始时间', minWidth:100, align:'center',templet: function (d) {
            //         return util.toDateString(d.startTime, "yyyy年MM月dd日")
            //     }},
            // {field: 'endTime', title: '结束时间', minWidth:100, align:'center',templet: function (d) {
            //         return util.toDateString(d.endTime, "yyyy年MM月dd日")
            //     }},
            {title: '操作', minWidth: 100, templet: '#infoListBar', fixed: "right", align: "center"}
        ]]
    });

    //列表操作
    table.on('tool(infoList)', function (obj) {
        var layEvent = obj.event,
            data = obj.data;

        if(layEvent === 'detail'){ //详情
            detailUser(data);
        }
    });

    //详情
    function detailUser(data){
        var index = layui.layer.open({
            title : "详情",
            type : 2,
            content : "item-detail.html",
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                body.find(".itemId").val(data.itemId);
                body.find(".itemName").val(data.itemName);
                body.find(".itemType").val(data.itemType);
                body.find(".itemCategory").val(data.itemCategory.title);
                body.find(".itemLevel").val(data.itemLevel.title);
                body.find(".contractNumber").val(data.contractNumber);
                body.find(".itemMoney").val(data.itemMoney);
                body.find(".createTime").val(util.toDateString(data.createTime, "yyyy"));
                body.find(".startTime").val(util.toDateString(data.startTime, "yyyy-MM-dd"));
                body.find(".endTime").val(util.toDateString(data.endTime, "yyyy-MM-dd"));
                body.find(".personName").val(data.itemPerson.teacherName);//负责人
                body.find(".memberName").val(data.memberName);//成员
                form.render();
            }
        });
        layui.layer.full(index);  //全屏
        window.sessionStorage.setItem("index",index);  //存放当前列表行数据
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }

    //exportCode:   1-系统管理员导出整个表 2-部门领导和部门负责人导出本学院全部 3-主持人导出全部 4-根据id导出
    //批量导出
    $(".exportBtn").click(function(){
        var checkStatus = table.checkStatus('infoListTable'),
            data = checkStatus.data,
            newsId = [];
        if(data.length > 0) {
            for (var i in data) {
                newsId.push(data[i].itemId);
            }
            $.ajax({
                url: '/teacher_files_war/biz/teacherItem_findByExport.action',
                data: {ids: newsId.join(','), exportCode: 4},
                dataType: 'json',
                success: function (res) {
                    if (res.code===0){
                        var data = res.data;
                        // 重点！！！如果后端给的数据顺序和映射关系不对，请执行梳理函数后导出
                        data = excel.filterExportData(data, {
                            itemId: 'itemId',
                            itemName: function(value, line, data) {
                                return {
                                    v: value,
                                    s: { font: { sz: 14, bold: true, color: { rgb: "FFFFAA00" } }, fill: { bgColor: { indexed: 64 }, fgColor: { rgb: "FFFF00" } } }
                                };
                            },
                            itemPerson: function (value, line, data) {
                                return value.teacherName;
                            },
                            itemType: function (value, line, data) {
                                if (value=='1') return '教改类';
                                if (value=='2') return '科研类';
                            },
                            itemCategory: function (value, line, data) {
                                return value.title;
                            },
                            createTime: function (value, line, data) {
                                return util.toDateString(value, "yyyy年");
                            },
                            itemMoney: function (value, line, data) {
                                return value+"/万元";
                            },
                            itemLevel: function (value, line, data) {
                                return value.title;
                            },
                            memberName: 'contractNumber',
                            contractNumber: 'contractNumber',
                            startTime: function (value, line, data) {
                                return util.toDateString(value, "yyyy年MM月dd日");
                            },
                            endTime: function (value, line, data) {
                                return util.toDateString(value, "yyyy年MM月dd日");
                            },

                        });
                        // 重点2！！！一般都需要加一个表头，表头的键名顺序需要与最终导出的数据一致
                        data.unshift({itemId: '编号', itemName: '项目名称', itemPerson: '主持人', awardee: '获奖人', itemType: '项目类型',
                            itemCategory: '项目类别', createTime: '立项时间', itemMoney: '项目经费', itemLevel: '项目级别', memberName: '成员',
                            contractNumber: '合同编号', startTime: '起始时间', endTime: '结束时间'})

                        excel.exportExcel({
                            sheet1: data
                        }, '我参与的教师项目.xlsx', 'xlsx');
                    }else {
                        layer.msg("导出失败");
                    }
                }
            })
        }else{
            layer.msg("请选择需要导出的记录");
        }
    });
});