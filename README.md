# Course Enrollment Backend

In short: Just a (I refrain to say _the most_) trivial demonstration of a monotholic backend app

## Introduction

## TODO

[ - ] Backend does not recognize URL encoding of space etc.

Currently the issue is circumvented by passing the params in payload (which actually _is_ better practice in my use case)

[ ] Add join query (for showing the user's enrollment status on `/api/courses` )

[ ] Add registration

[ ] Add course cap & waitlist

Requires updating 2 tables (course & user_course) atomically:

- 10 slots for each WL. When a 'formal' slot becomes available, auto-enroll
  the 1st on the list, advance the rest by 1 position. The 10th slot on WL should then become available.  


[ ] JHipster walkthrough
