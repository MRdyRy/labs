Start minikube :
- sudo minikube start --force

Dashboard :
- sudo minikube dashboard
- sudo minikube dashboard --url

Apply:
- sudo kubectl apply -f sample-deployment.yaml
- sudo kubectl apply -f sample-pod.yaml
- sudo kubectl apply -f . (melakukan apply terhadap semua object yg ada, jika sudah di apply & tidak ada perubahan akan di ignore)

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

<ul>
<li>ClusterIp = mengekspos yg ada didalam pod ke luar pod</li>
<li>NodePort = mengekspos akses pod ke semua vm yg ada di dalam cluster. Menjadikan akses dari luar cluster yg masih dalam satu network dengan cluster tersebut diperbolehkan.</li>
<li>LoadBalancer = ekspos akses ke internet/public</li>
  
</ul>



service object :
Sample:
<code>
apiVersion: v1
kind: Service
metadata:
  name: my-service1
spec:
  type: NodePort / ClusterIp / LoadBalancer
  selector:
    name: my-app1
  ports:
    - port: 8080 // port service
      targetPort: 9050 // target port yg akan di redirect ke port pd my-app1 di cluster ketika service object dipanggil
</code>



check route :
- sudo minikube service my-service1
