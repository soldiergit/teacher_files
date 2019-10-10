layui.use(['form', 'layer', 'table', 'laytpl', 'util', 'laydate'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        util = layui.util,
        laydate = layui.laydate,
        table = layui.table;

    var teacherId = window.sessionStorage.getItem("teacherId");
    //信息列表
    var tableIns = table.render({
        elem: '#infoList',//数据表格id
        url: '/teacher_files_war/biz/paper_findByAuthor.action?teacherId=' + teacherId,//数据接口
        page: true,//开启分页
        height: "full-125",//容器高度
        limits: [10, 15, 20, 25],
        limit: 20,
        toolbar: '#toolbar',//头部工具栏
        title: '我的学术论文',//用于导出
        id: "infoListTable",//给它一个id，用于下面更新表单内容
        cols: [[//表头
            {type: "checkbox", fixed: "left", width: 50},
            {field: 'paperName', title: '论文名称', minWidth: 100, align: 'center'},
            {field: 'paperType', title: '论文类型', minWidth: 100, align: 'center', templet: function (d) {
                    if (d.paperType == '1') return '教改类';
                    if (d.paperType == '2') return '科研类';
                }},
            {field: 'teacherType', title: '作者类型', minWidth: 100, align: 'center', templet: function (d) {
                    if (d.teacherType == '1') return '第一作者或通讯作者';
                    if (d.teacherType == '2') return '独立作者';
                    if (d.teacherType == '3') return '其它';
                }},
            {field: 'signUnit', title: '署名单位', minWidth: 100, align: 'center', templet: function (d) {
                    if (d.signUnit == '1') return '第一署名单位';
                    if (d.signUnit == '2') return '第二署名单位';
                    if (d.signUnit == '3') return '其它';
                }},
            {field: 'periodicalName', title: '发布期刊名称', minWidth: 100, align: 'center'},
            {field: 'periodicalNumber', title: '期刊号', minWidth: 100, align: 'center'},
            {field: 'publishTime', title: '发布时间', minWidth: 100, align: 'center', templet: function (d) {
                    return util.toDateString(d.publishTime, "yyyy年MM月dd日");
                }},
            {field: 'paperGrade', title: '论文等级', minWidth: 100, align: 'center', templet: function (d) {
                    return d.paperGrade.title;
                }},
            {field: 'paperTitle', title: '论文标题', minWidth: 100, align: 'center'},
            {field: 'teacherName', title: '作者', minWidth: 100, align: 'center', templet: function (d) {
                    return d.teacher.teacherName;
                }},
            {title: '操作', minWidth: 175, templet: '#infoListBar', fixed: "right", align: "center"}
        ]]
    });

    var $ = layui.$, active = {
        reload: function(){
            var dataReload = $('#dataReload');
            //执行重载
            table.reload('infoListTable', {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {key: "0,"+dataReload.val()}
            }, 'data');
        }
    };

    $('.layui-card-body .reloadBtn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    $(".addBtn").click(function(){
        addUser();
    });

    //列表操作
    table.on('tool(infoList)', function (obj) {
        var layEvent = obj.event,
            data = obj.data;

        if (layEvent === 'edit') { //编辑
            addUser(data);
        } else if (layEvent === 'del') { //删除
            layer.confirm('确定删除此记录？', {icon: 3, title: '提示信息'}, function (index) {
                $.get("/teacher_files_war/biz/paper_delete.action", {
                    paperId: data.paperId  //将需要删除的newsId作为参数传入
                }, function (data) {
                    if (data.code === 0) {
                        layer.msg("删除成功");
                    } else {
                        layer.msg("删除失败");
                    }
                    tableIns.reload();
                    layer.close(index);
                })
            });
        } else if (layEvent === 'detail') { //详情
            detailUser(data)
        }
    });

    //批量删除
    $(".delBtn").click(function(){
        var checkStatus = table.checkStatus('infoListTable'),
            data = checkStatus.data,
            newsId = [];
        if(data.length > 0) {
            for (var i in data) {
                newsId.push(data[i].paperId);
            }
            layer.confirm('确定删除选中记录？', {icon: 3, title: '提示信息'}, function (index) {
                $.post("/teacher_files_war/biz/paper_deleteBatch.action",{
                    ids : newsId.join(',') //将需要删除的newsId作为参数传入
                },function(data){
                    if (data.code===0){
                        layer.msg("删除成功");
                    }else {
                        layer.msg("删除失败");
                    }
                    tableIns.reload();
                    layer.close(index);
                })
            })
        }else{
            layer.msg("请选择需要删除的论文");
        }
    });

    //添加用户
    function addUser(edit){
        var index = layui.layer.open({
            type: 2,
            title: '添加文章',
            content: 'paper-add.html',
            maxmin: true,
            area: ['800px', '800px'],
            btn: ['确定', '取消'],
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                if(edit){
                    body.find(".Id").val(edit.paperId);
                    body.find(".paperName").val(edit.paperName);
                    body.find(".paperTitle").val(edit.paperTitle);
                    body.find(".teacherType").val(edit.teacherType);  //作者类型
                    body.find(".paperType").val(edit.paperType);
                    body.find(".signUnit").val(edit.signUnit);
                    body.find(".periodicalName").val(edit.periodicalName);
                    body.find(".periodicalNumber").val(edit.periodicalNumber);
                    body.find(".publishTime").val(util.toDateString(edit.publishTime, "yyyy-MM-dd"));
                    body.find(".teacherHide").val(edit.teacher.teacherId);//使用中间变量选中作者
                    body.find(".personHide").val(edit.teacher.teacherId);//使用中间变量记录作者
                    body.find(".memberHide").val(edit.itemMember);//使用中间变量记录成员
                    body.find(".paperGradeHide").val(edit.paperGrade.id);//使用中间变量选中论文等级
                    body.find(".updateFlag").val(1);//更新

                    //回显示文件
                    $.ajax({
                        type: "POST",
                        url: '/teacher_files_war/biz/paperAnnex_findByPaperId.action',//数据接口
                        data: {paperId: edit.paperId},
                        success: function (data) {
                            if (data.code === 0) {
                                $.each(data.data, function (index, item) {
                                    var oldtr = $(['<tr id="upload-'+ index +'">',
                                        '<td>'+ item.fileName +'</td>',
                                        // '<td>'+ (file.size/1014).toFixed(1) +'kb</td>',
                                        '<td></td>',
                                        '<td>已上传</td>',
                                        '<td>',
                                        '<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>',
                                        '</td>',
                                        '</tr>'].join(''));

                                    //删除
                                    oldtr.find('.demo-delete').on('click', function(){
                                        $.post("/teacher_files_war/biz/paperAnnex_delete.action", {
                                            paperAnnexId: item.paperAnnexId //将需要删除的matchAnnexId作为参数传入
                                        }, function (data) {
                                            if (data.code === 0) {
                                                layer.msg("删除成功");
                                            } else {
                                                layer.msg("删除失败");
                                            }
                                            tableIns.reload();
                                            layer.close(index);
                                        });
                                        oldtr.remove();//删除当前
                                    });

                                    body.find("#demoList").append(oldtr);
                                });
                            } else {
                                layer.msg("未知错误，请联系管理员！" + data.msg);
                            }

                        },
                        error: function () {
                            layer.msg("可能是因为网络原因操作失败了，请重试，若多次重试不成功，请于网站管理员联系");
                        }
                    });

                    form.render();
                }
            },
            yes: function (index, layero) {
                //点击确认触发 iframe 内容中的按钮提交
                var submit = layero.find('iframe').contents().find("#addUser");
                submit.click();
            }
        });
        // layui.layer.full(index);  //全屏
        window.sessionStorage.setItem("index",index);  //存放当前列表行数据
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }

    //用户详情
    function detailUser(data){
        var index = layui.layer.open({
            type: 2,
            title: '详情',
            content: 'paper-detail.html',
            success : function(layero, index){
                var body = layui.layer.getChildFrame('body', index);
                body.find(".paperId").val(data.paperId);
                body.find(".paperName").val(data.paperName);
                body.find(".paperTitle").val(data.paperTitle);
                body.find(".teacherName").val(data.teacher.teacherName);
                body.find(".teacherType").val(data.teacherType);  //作者类型
                body.find(".paperType").val(data.paperType);
                body.find(".paperGrade").val(data.paperGrade.title);
                body.find(".signUnit").val(data.signUnit);
                body.find(".publishTime").val(util.toDateString(data.publishTime, "yyyy年MM月dd日"));
                body.find(".periodicalName").val(data.periodicalName);
                body.find(".periodicalNumber").val(data.periodicalNumber);
                body.find(".memberName").val(data.memberName);//成员

                var str = "";   //附件详情
                $.ajax({
                    type: "POST",
                    url: '/teacher_files_war/biz/paperAnnex_findByPaperId.action',//数据接口
                    data: {paperId: data.paperId},
                    success: function (data) {
                        if (data.code === 0) {
                            $.each(data.data, function (index, item) {
                                str += '<a class="fileCss" href="/teacher_files_war/download/download_downloadFile.action?' +
                                    'downloadPath=' + item.filePath + '&filename=' + item.fileName + '">' + item.fileName + '</a><br>';
                                // 'downloadPath=' + item.path + '&filename=《' + data.paperName + '》附件' + index + '">《' + data.paperName + '》附件' + index + '</a><br>';
                            });
                            body.find("#manyFile").html(str);
                        }
                    }});

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

    /**
     * 0:根据名称等String类型查询
     * 1：年度
     * 2：论文等级--paper_grade_id外键
     * 3：论文类型--paperType论文类型：科研、教改
     * 4：根据指导老师所属教研室--teacher.unitIds
     * 5：作者id
     */

    //执行一个laydate实例
    laydate.render({elem: '#queryTime' , type: 'year', done: function(value, date, endDate){//控件选择完毕后的回调
            table.reload('infoListTable', {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {key: "1,"+value}
            }, 'data');
        }});
    $.post("/teacher_files_war/biz/paperGrade_findAll.action", function (data) {//--论文等级
        $('#paperGrade').append(new Option("请选择", ""));
        $.each(data.data, function (index, item) {
            $('#paperGrade').append(new Option(item.title, "2,"+item.id));
        });
        //重新渲染select
        form.render('select');
    });

    //当用户选中所属部门时，重载表格  -- unitId:lay-filter绑定的名称
    form.on("select(paperGrade)", function (data) {reloadTable(data)});
    form.on("select(paperType)", function (data) {reloadTable(data)});
    function reloadTable(data) {
        var key = data.value=="" ? null : data.value;
        //执行重载
        table.reload('infoListTable', {
            page: {
                curr: 1 //重新从第 1 页开始
            },
            where: {key: key}
        }, 'data');
    }
});