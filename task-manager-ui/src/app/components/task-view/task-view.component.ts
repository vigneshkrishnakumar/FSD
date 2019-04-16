import { Component, OnInit } from '@angular/core';
import { TaskService } from 'src/app/services/task.service';
import {formatDate} from '@angular/common';

@Component({
  selector: 'app-task-view',
  templateUrl: './task-view.component.html',
  styleUrls: ['./task-view.component.css']
})
export class TaskViewComponent implements OnInit {

  tasks : Array<any> = [];
  id : number
  task : any = {}
  status: string = "";
  endDate: string = formatDate(new Date(), 'yyyy-MM-dd', 'en');
  constructor(private taskService : TaskService) { }

  ngOnInit() {
    this.taskService.fetchTasks()
    .then((res) => {
      console.log(res);
      this.tasks = res;
    })
  }

  isEnded(date) {
    console.log("End date "+date);
    if(date <= this.endDate) {
      return true;
    } else {
      return false;
    }
  }
  
  endTask(id) {
    for(let val in this.tasks) {
      if(this.tasks[val].newTask.taskId == id) {
        this.tasks[val].newTask.endDate = formatDate(new Date(), 'yyyy-MM-dd', 'en');
        console.log("Ended task "+this.tasks[val]);
        this.taskService.updateTask(JSON.stringify(this.tasks[val]), id)
        .then(res => {
            console.log(res);
            if (res.newTask.taskId > 0) {
              this.status = 'Task Ended successfully!!';
            }
        }, err => {
            console.log('server err');
            console.log(err);
        })
        .catch(err => {
            console.log('client err');
            console.log(err);
        })
      }
    }
  }

}
