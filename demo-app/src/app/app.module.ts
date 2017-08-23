import { HeroService } from './hero.service';
import { HeroDetailsComponent } from './hero-details/hero-details.component';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent} from './app.component';

@NgModule({
    declarations: [
        AppComponent,
        HeroDetailsComponent
    ],
    imports: [
        BrowserModule,
        FormsModule
    ],
    providers: [HeroService],
    bootstrap: [AppComponent]
})
export class AppModule {
    
  
}
