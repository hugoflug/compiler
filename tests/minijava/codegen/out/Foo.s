.source Test.mj
.class public Foo
.super java/lang/Object
.field public a I
.field public b I
.field public c I
.field public foo LFoo;
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
iconst_0
iconst_1
ixor
ifeq l0
getstatic java/lang/System/out Ljava/io/PrintStream;
ldc 666
invokevirtual java/io/PrintStream/println(I)V
l0:
ldc 0
ireturn
.end method
.method public boo()I
.limit stack 100
.limit locals 100
iconst_1
ireturn
.end method
.method public add(II)I
.limit stack 100
.limit locals 100
ldc 56
istore 3
iload 1
iload 2
iadd
ireturn
.end method
