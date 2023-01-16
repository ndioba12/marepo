import {AbstractControl, ValidationErrors, ValidatorFn} from '@angular/forms';

export class CustomPasswordValidators {
  static patternValidator(regex: RegExp, error: ValidationErrors): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } => {
      if (!control.value) {
        // if control is empty return no error
        return null;
      }

      // test the value of the control against the regexp supplied
      const valid = regex.test(control.value);

      // if true, return no error (no error), else return error passed in the second parameter
      return valid ? null : error;
    };
  }

  static PasswordValidator(control: AbstractControl): { [key: string]: boolean } | null {
    const newpassword = control.get('newPassword');
    const confirmPassword = control.get('newPasswordConfirm');
    if (confirmPassword.pristine || newpassword.pristine) {
      return null;
    }
    // tslint:disable-next-line:triple-equals
    return newpassword && confirmPassword && newpassword.value != confirmPassword.value ?
      {misMatch: true} :
      null;
  }

  static UpdatePasswordValidator(controls: AbstractControl, newPasswordControl: string, confirmPasswordControl: string): { [key: string]: boolean } | null {
    const control = controls.get('newPassword');
    const matchingControl = controls.get('confPassword');
    if (control.pristine || matchingControl.pristine) {
      return null;
    }

    return control && matchingControl && control.value != control.value ?
      {'DonMatch': true} :
      null;
  }
}


export class CustomeDateValidators {
  static fromToDate(fromDateField: string, toDateField: string, errorName: string = 'fromToDate'): ValidatorFn {
    return (formGroup: AbstractControl): { [key: string]: boolean } | null => {
      const fromDate = formGroup.get(fromDateField).value;
      const toDate = formGroup.get(toDateField).value;
      // Ausing the fromDate and toDate are numbers. In not convert them first after null check
      if ((fromDate !== null && toDate !== null) && fromDate > toDate) {
        return {[errorName]: true};
      }
      return null;
    };
  }
}

export class CustomMinMaxValidator {
  static minToMax(minField: string, maxField: string, checkTheEquality: boolean = false, errorName: string = 'minToMax'): ValidatorFn {
    return (formGroup: AbstractControl): { [key: string]: boolean } | null => {
      const min = formGroup.get(minField).value;
      const max = formGroup.get(maxField).value;

      if (checkTheEquality) {
        // tslint:disable-next-line:radix
        if ((min !== null && max !== null) && parseInt(min) > parseInt(max)) {
          return {[`${errorName}_${maxField}`]: true};
        }
      } else {
        // tslint:disable-next-line:radix
        if ((min !== null && max !== null) && parseInt(min) >= parseInt(max)) {
          return {[`${errorName}_${maxField}`]: true};
        }
      }
      return null;
    };
  }
}

export class CustomeInputValidators {
  static fromToDate(fromInputField: string, toInputField: string, errorName: string = 'fromToInput'): ValidatorFn {
    return (formGroup: AbstractControl): { [key: string]: boolean } | null => {
      const fromInput = formGroup.get(fromInputField).value;
      const toInput = formGroup.get(toInputField).value;
      // Ausing the fromDate and toDate are numbers. In not convert them first after null check
      if ((fromInput !== null && toInput !== null) && fromInput == toInput) {
        return {[errorName]: true};
      }
      return null;
    };
  }
}

export class Validation {
  static currentYearValidation(errorName: string = 'dateInput'): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } => {
      if (!control.value) {
        // if control is empty return no error
        return null;
      }

      let currentYear = new Date().getUTCFullYear();
      let selectedYear = new Date(control.value).getUTCFullYear();
      // if true, return no error (no error), else return error passed in the second parameter
      return currentYear === selectedYear ? null : {[`${errorName}`]: true};
    };
  }
}
