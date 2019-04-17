import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { TaskService } from 'src/app/services/task.service';

@Component({
  selector: 'app-task-edit',
  templateUrl: './task-edit.component.html',
  styleUrls: ['./task-edit.component.css']
})
export class TaskEditComponent implements OnInit {

  id : number
  task : any = {}
  title: string = "Update Task";
  status: string = "";
  constructor(private taskService : TaskService, private route : ActivatedRoute, private location :Location) { }

  ngOnInit() {
    const id = +this.route.snapshot.paramMap.get('id');
    console.log(id);
    if(+id >= 0){
        this.id = +id;
        this.task["taskId"] = this.id;
        this.getTask(this.id);
    }
  }

  getTask(id) {
    this.taskService.getTask(id)
    .then((res) => {
      console.log(res);
      this.task = res;
    })
  }

  updateTask() {
    this.taskService.updateTask(JSON.stringify(this.task), this.id)
        .then(res => {
            console.log(res);
            if (res.newTask.taskId > 0) {
              this.status = 'Task updated successfully!!';
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

  goBack() {
    this.location.back();
  }
}
