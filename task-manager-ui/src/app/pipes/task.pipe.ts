import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'taskfilter'
})
export class TaskPipe implements PipeTransform {

    transform(value: Array<any>, 
        taskName?: string,
        parentTask?: string,
        priorityFrom?: string,
        priorityTo?: string,
        startDate?: string,
        endDate?: string) {

        if(taskName.trim() == '' && 
        parentTask.trim() == '' && 
        priorityFrom.trim() == '' && 
        priorityTo.trim() == '' && 
        startDate.trim() == '' && 
        endDate.trim() == ''){
            return value;
        }

        if(taskName.trim() != '') {
            let filteredTasks = value.filter(val => {
                return val.newTask.taskName.includes(taskName)
            });
            return filteredTasks
        } 
        if(parentTask.trim() != '') {
            let filteredTasks = value.filter(val => {
                return val.parentTask.includes(parentTask)
            });
            return filteredTasks
        }
        if(startDate.trim() != '') {
            let filteredTasks = value.filter(val => {
                return val.newTask.startDate.includes(startDate)
            });
            return filteredTasks
        }
        if(endDate.trim() != '') {
            let filteredTasks = value.filter(val => {
                return val.newTask.endDate.includes(endDate)
            });
            return filteredTasks
        }
        if(priorityFrom.trim() != '' && priorityTo.trim() != '') {
            let priorityTasks = [];
            for(let val in value) {
                console.log('Priority From '+value[val].newTask.priority);
                if(value[val].newTask.priority >=priorityFrom && value[val].newTask.priority <=priorityTo) {
                    priorityTasks.push(value[val]);
                }
            }
            console.log('Final '+priorityTasks);
            return priorityTasks
        }
    }

}