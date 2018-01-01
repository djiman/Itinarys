import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { HttpClientModule } from '@angular/common/http';
import { MatTableModule } from '@angular/material';

import { AppComponent } from './app.component';
import { TrajetService } from './services/trajet.service';
import { TrajettableComponent } from './components/trajettable/trajettable.component'

@NgModule({
  declarations: [
    AppComponent, TrajettableComponent
  ],
  imports: [
  	BrowserModule,
    FormsModule,
    HttpModule,
    HttpClientModule,
    MatTableModule
  ],
  providers: [TrajetService],
  bootstrap: [AppComponent, TrajettableComponent]
})
export class AppModule { }
