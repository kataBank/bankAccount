import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MenuComponent } from './menu/menu.component';
import { AccountComponent } from './account/account.component';
import { OperationComponent } from './operation/operation.component';
import { ClientComponent } from './client/client.component';
import {HttpClientModule} from '@angular/common/http';
import { ClientListComponent } from './client/client-list/client-list.component';
import { ClientItemComponent } from './client/client-list/client-item/client-item.component';
import { FooterComponent } from './footer/footer.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { PrintPageComponent } from './print-page/print-page.component';




@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    AccountComponent,
    OperationComponent,
    ClientComponent,
    ClientListComponent,
    ClientItemComponent,
    FooterComponent,
    LoginComponent,
    HomeComponent,
    PrintPageComponent,
  ],
  imports: [
    ReactiveFormsModule,
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
   
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
