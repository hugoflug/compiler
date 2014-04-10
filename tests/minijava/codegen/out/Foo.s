.source Test.mj
.class public Foo
.super java/lang/Object
.field public a I
.method public <init>()V
.limit stack 100
.limit locals 100
aload_0
invokespecial java/lang/Object/<init>()V
return
.end method
.method public foo()I
.limit stack 100
.limit locals 100
iconst_1
ifeq l4
ldc 3
aload_0
swap
putfield Foo/a I
l4:
ldc 5
ireturn
.end method
.method public boo()I
.limit stack 100
.limit locals 100
iconst_1
ireturn
.end method
