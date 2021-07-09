from django.urls import path
from . import views

urlpatterns = [
    # path(route name, function, name to reference later)
    path("", views.index, name="index"),
    path("<str:name>", views.greet, name="greet")
]