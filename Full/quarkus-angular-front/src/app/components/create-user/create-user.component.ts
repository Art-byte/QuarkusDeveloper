import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/entity/User';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {

  user: User = new User();
  submitted = false;

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
  }

  newUser(): void{
    this.submitted = false;
    this.user = new User();
  }


  onSubmit(){
    this.submitted = true;
    this.save();
  }

  save(){
    this.userService.createUser(this.user).subscribe(data =>
     { console.log(data);
      this.user = new User();
      this.gotoList();
    },
    error => console.log(error));
  }

  gotoList(){
    this.router.navigate(['/users']);
  }
}
