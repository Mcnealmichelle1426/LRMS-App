/**
 * Created by kelly on 10/02/2015.
 */
(function(){

    'user strict';

    angular.module('VetechLawApp').controller('CaseClassificationController',CaseClassificationController);

    CaseClassificationController.$inject = ['lodash','CaseClassificationService'];

    function CaseClassificationController(_,CaseClassificationService){
        var me = this;
        me.caseClassification = {};
        me.getAllCaseClassificationController = getAllCaseClassificationController;
        me.saveChanges = saveChanges;
        me.editData = editData;
        me.isSaving = null;
        me.isDisabled = null;
        me.cancelForm = cancelForm;
        me.caseClassifications = [];
        me.deactivateItem = deactivateItem;

        me.caseClassifications = [
        {
            id:1,
            active:true,
            code:'code 1',
            name:"name 1",
            description:'description'
        },{
            id:2,
            active:false,
            code:'code 2',
            name:"name 2",
            description:'description'
        },{
            id:3,
            active:false,
            code:'code 3',
            name:"name 3",
            description:'description'
        },{
            id:4,
            active:true,
            code:'code 4',
            name:"name 4",
            description:'description'
        },{
            id:5,
            active:true,
            code:'code 5',
            name:"name 5",
            description:'description'
        }
        ];

        getAllCaseClassificationController();

        function getAllCaseClassificationController(){
            console.log(_.isArray(me.caseClassifications));
            //CaseClassificationService.getCaseClass().then(function(data){
            //    console.log(data);
            //    me.data = data.results;
            //});

        }
        function saveChanges(){
            //console.log(me.caseClassification);
            me.isSaving = true;
            me.isDisabled =true;
            if(me.caseClassification.id){
                CaseClassificationService.editCaseClassification(me.caseClassification, me.caseClassification.id).then(function(data){
                    me.isSaving = false;
                    me.isDisabled =false;
                    var index  = _.findIndex(me.caseClassifications,{id:me.caseClassification.id});
                    if(index > -1){
                        _.extend(me.caseClassifications[index],data);
                    }
                },function(error){
                    if(error.status !== 200){
                        me.isSaving = false;
                        me.isDisabled =false;
                    }
                });
            }else{
                me.caseClassifications.push(me.caseClassification);
                CaseClassificationService.saveCaseClassification(me.caseClassification).then(function(data){
                    me.isSaving = false;
                    me.isDisabled =false;
                },function(error){
                    if(error.status !== 200){
                        me.isSaving = false;
                        me.isDisabled =false;
                    }
                });
            }
        }
        function editData(data){
            me.caseClassification =  data;
        }

        function deactivateItem(data){
            var index  = _.findIndex(me.caseClassifications,{id:data.id});
            if(index > -1){
                me.caseClassifications.splice(index,1);
            }
        }

        function cancelForm(data){
            console.log(data)
            //me.caseClassification = {};
        }
    }
})();


