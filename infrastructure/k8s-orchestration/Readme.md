Start minikube :
- sudo minikube start --force

Dashboard :
- sudo minikube dashboard
- sudo minikube dashboard --url

Apply:
- sudo kubectl apply sample-deployment.yaml
- sudo kubectl apply sample-pod.yaml

Check pod / deployment:
- sudo kubectl get pods
- sudo kubectl get deployments
- sudo kubectl describe pod my-pod1
- sudo kubectl describe deployment my-app-deployment-1

Delete pod / deployment:
- sudo kubectl delete pod my-pod1
- sudo kubectl delete deploymeny my-app-deployment-1


disarankan menggunakan deployment object daripada pod object.
karna di dalam deployment object memanage replica set, pod.
