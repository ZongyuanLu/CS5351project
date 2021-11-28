import {Component, Inject, OnInit} from '@angular/core';
import { Scrum } from '../model/scrum/scrum';
import { ScrumService } from '../service/scrum-service.service';
import { MatDialog, MatDialogConfig } from '@angular/material';
import { ScrumDialogComponent } from '../scrum-dialog/scrum-dialog.component';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import {Task} from "../model/task/task";
import {TaskDialogComponent} from "../task-dialog/task-dialog.component";
import {MAT_DIALOG_DATA} from "@angular/material/dialog";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  scrumList: Scrum[];
  //scrum:Scrum;

  constructor(
    private scrumService: ScrumService,
    //@Inject(MAT_DIALOG_DATA) data,
    private dialog: MatDialog
  ) {//this.scrum = data.scrum;
  //this.scrumList = data.scrumList;
  }

  ngOnInit() {
    this.retrieveAllScrumBoards();
  }

  openDialogForNewScrum(): void {
    this.openDialog('Create New Scrum', new Scrum())
    /**
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.data = {
      scrum: new Scrum()
    };
    this.dialog.open(ScrumDialogComponent, dialogConfig)
     **/
  }

  private retrieveAllScrumBoards(): void {
    this.scrumService.retrieveAllScrumBoards().subscribe(

      response => {
        this.scrumList = response;
      }
    )
  }

  openScrumDialog(event): void {
    let scrumId = event.srcElement.id;

    this.scrumService.getScrumById(scrumId).subscribe(
      response => {
        this.openDialog('Update Scrum', response);
      }
    )
  }
  private openDialog(title: string, scrum: Scrum): void {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.data = {
      title: title,
      scrum: scrum
    };
    this.dialog.open(ScrumDialogComponent, dialogConfig)
  }

  /**
  delete(event): void {
    this.scrumService.deleteScrum(this.scrum).subscribe();
  }
   **/
}
